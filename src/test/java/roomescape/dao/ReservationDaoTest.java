package roomescape.dao;

import java.time.LocalDate;
import java.util.List;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import roomescape.domain.Reservation;

@JdbcTest
@Sql(scripts = "/data-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
class ReservationDaoTest {
    private final ReservationDao reservationDao;

    @Autowired
    public ReservationDaoTest(DataSource dataSource) {
        this.reservationDao = new ReservationDao(dataSource);
    }

    @Test
    @DisplayName("데이터들이 잘 저장되는지 확인.")
    void saveReservation() {
        Reservation reservation = new Reservation("범블비", LocalDate.now(), 1L);
        reservationDao.save(reservation);

        Assertions.assertThat(reservation).isNotNull();
    }

    @Test
    @DisplayName("데이터들을 잘 가져오는지 확인.")
    void getReservations() {
        List<Reservation> reservations = reservationDao.findAll();

        Assertions.assertThat(reservations.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("데이터를 잘 지우는지 확인.")
    void deleteReservations() {
        reservationDao.deleteById(1L);

        Assertions.assertThat(reservationDao.findAll().size()).isEqualTo(1);
    }
}
