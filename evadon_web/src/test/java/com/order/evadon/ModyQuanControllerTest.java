package com.order.evadon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.evadon.entity.ProductEntity;
import com.order.evadon.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(MockitoExtension.class)
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@Rollback(value = false)
@DisplayName("수정컨트롤러")
class ModyQuanControllerTest {

    @Container
    static DockerComposeContainer dockerComposeContainer = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModyQuanController modyQuanController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext ctx;


    @BeforeEach
    public void setUp() {
        //MockMvc add utf-8 설정
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .build();
    }

    @DisplayName("재고 1개 차감 버튼")
    @Test
    void pop() throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        QuanDTO quanDTO = new QuanDTO();
        quanDTO.setId(1);
        ProductEntity productEntity = new ProductEntity(1, "쫄면", 1, 0, "0001", 2, 8);
        productRepository.save(productEntity);
        productRepository.flush();


        logger.info("================ {} ============", "재고 1개일때 1개 차감");
        mockMvc.perform(
                        post("/pop")
                                .content(objectMapper.writeValueAsString(
                                                productRepository.findById(quanDTO.getId()).get()
                                        )
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("ok"));


        logger.info("================ {} ============", "재고 0개일때 1개 차감");
        mockMvc.perform(
                        post("/pop")
                                .content(objectMapper.writeValueAsString(
                                                productRepository.findById(quanDTO.getId()).get()
                                        )
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("이미 재고가 0개 입니다."));
    }

}