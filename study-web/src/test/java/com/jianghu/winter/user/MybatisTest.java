package com.jianghu.winter.user;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author daniel.hu
 */
@Slf4j
public class MybatisTest {

    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private static Connection connection;
    private UserService userService;

    @BeforeClass
    public static void init() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        connection = sqlSessionFactory.openSession().getConnection();
    }

    @AfterClass
    public static void closeConn() throws SQLException {
        connection.close();
    }

    @Before
    public void setUp() throws Exception {
        new ScriptRunner(connection).runScript(Resources.getResourceAsReader("import.sql"));
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userService = new UserService(userMapper);
    }

    @After
    public void tearDown() {
        sqlSession.close();
    }

    @Test
    public void test_selectOne() {
        UserEntity entity = userService.get(1L);
        assertEquals("daniel", entity.getAccount());
        System.out.println("【Get】: " + JSON.toJSONString(entity, true));
    }

    @Test
    public void test_selectAll() {
        List<UserEntity> entities = userService.find();
        assertEquals(4, entities.size());
        System.out.println("【findAll】: " + JSON.toJSONString(entities, true));
    }

    @Test
    public void test_delete() {
        userService.delete(4L);
        List<UserEntity> entities = userService.find();
        assertEquals(3, entities.size());
        System.out.println("【Delete + FindAll】: " + JSON.toJSONString(entities, true));
    }

    @Test
    public void test_save() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAccount("test");
        userEntity.setEmail("123456@qq.com");
        userEntity.setNickName("James");
        userEntity.setMobile("18312341234");
        userEntity.setPassword("123456");
        userEntity.setValid(true);
        userEntity.setUserType(UserType.SYSTEM);

        userService.insert(userEntity);
        UserEntity entity = userService.get(5L);
        assertThat(entity)
                .hasFieldOrPropertyWithValue("id", 5L)
                .hasFieldOrPropertyWithValue("account", "test")
                .hasFieldOrPropertyWithValue("email", "123456@qq.com")
                .hasFieldOrPropertyWithValue("mobile", "18312341234")
                .hasFieldOrPropertyWithValue("nickName", "James")
                .hasFieldOrPropertyWithValue("password", "123456")
                .hasFieldOrPropertyWithValue("valid", true)
        ;
        System.out.println("【Add + Get】: " + JSON.toJSONString(entity, true));
    }

    @Test
    public void test_update() {
        UserEntity entity = userService.get(1L);
        entity.setUserName("娃哈哈");
        userService.update(entity);
        UserEntity afterUpdateEntity = userService.get(1L);
        assertEquals("娃哈哈", afterUpdateEntity.getUserName());
        System.out.println("【Update + Get】: " + JSON.toJSONString(afterUpdateEntity, true));
    }

}
