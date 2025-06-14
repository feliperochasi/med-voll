package br.com.feliperochasi.med.voll.api.controller;

import br.com.feliperochasi.med.voll.api.domain.consultation.DetailsConsultationData;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultation;
import br.com.feliperochasi.med.voll.api.domain.consultation.ScheduleConsultationData;
import br.com.feliperochasi.med.voll.api.domain.medic.Specialised;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleConsultationData> scheduleConsultationDataJSON;

    @Autowired
    private JacksonTester<DetailsConsultationData> detailsConsultationDataJSON;

    @MockBean
    private ScheduleConsultation scheduleConsultation;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void scheduleFirst() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void scheduleSecond() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialised = Specialised.ORTOPEDIA;
        var detailsData = new DetailsConsultationData(null, 21L, 5L, date);

        when(scheduleConsultation.scheduler(any())).thenReturn(detailsData);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(scheduleConsultationDataJSON.write(
                                        new ScheduleConsultationData(21L, 5L, date, specialised)
                                ).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var exceptedJson = detailsConsultationDataJSON.write(
                detailsData
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(exceptedJson);
    }
}