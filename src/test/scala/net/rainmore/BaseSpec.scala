package net.rainmore

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = Array(classOf[Application]))
abstract class BaseSpec {

    @Transactional
    def withTransaction[T](action: => T): T = action
}