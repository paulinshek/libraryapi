package library_project.repos;

import org.junit.Test;

/*
 * Try JUnit here
 */
public class ReservationRepositoryTests {
    private ReservationRepository resRepo;

    public ReservationRepositoryTests() {
        resRepo = new ReservationRepository();
    }

    @Test
    public void testRepositoryIsEmptyAtStart() {
        assert(!resRepo.getAll().hasNext());
    }


}
