package roomescape.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import roomescape.controller.dto.ReservationRequest;
import roomescape.domain.Reservation;
import roomescape.repository.ReservationRepository;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    private final Reservation reservation = new Reservation(1L, "polla", LocalDateTime.now());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationRepository reservationRepository;

    @Test
    @DisplayName("예약 정보를 잘 저장하는지 확인한다.")
    void saveReservation() throws Exception {
        Mockito.when(reservationRepository.saveReservation(any()))
                .thenReturn(reservation);

        String content = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(
                        new ReservationRequest(reservation.getDate(), "polla", reservation.getTime()));

        mockMvc.perform(post("/reservations")
                        .content(content)
                        .contentType("application/Json")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 정보를 잘 불러오는지 확인한다.")
    void findAllReservations() throws Exception {
        Mockito.when(reservationRepository.findAllReservation())
                .thenReturn(List.of(reservation));

        mockMvc.perform(get("/reservations"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 정보를 잘 지우는지 확인한다.")
    void deleteReservation() throws Exception {
        mockMvc.perform(delete("/reservations/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
