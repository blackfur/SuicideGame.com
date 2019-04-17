package com.fakeghost.bbs;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;  
import java.util.List;
import org.springframework.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class ModelMapperTest {
    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Autowired
    @Qualifier("userMapper")
      UserMapper userMapper;

    @Test
    public void testQueryList() {
       //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
         //ctx.register(AppConfig.class, OtherConfig.class);
         //ctx.register(AdditionalConfig.class);
         //ctx.refresh();
       assertThat(userMapper, instanceOf(UserMapper.class));

       List<User> rows = userMapper.users(32);
       if(rows != null && rows.size() >0){
         for(int i = 0; i< rows.size(); i++){
            User row = rows.get(i);
            int id = row.getId();
            String nickname = row.getNickname();
            log.info("index: " + i + ", id: " + id + ", nickname: " + nickname);
         }
         User one = rows.get(0);
         assertThat(one.getNickname(), is(equalTo("Jenkins")));
       }
    }
}

