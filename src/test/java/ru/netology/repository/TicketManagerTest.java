package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.ticket.Ticket;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManagerTest {

    Ticket item1 = new Ticket(1, 20000, "DME", "SSH",300);
    Ticket item2 = new Ticket(2, 22000, "SVO", "HRG",320);
    Ticket item3 = new Ticket(3, 40000, "SVO", "VVO",510);
    Ticket item4 = new Ticket(4, 18000, "SVO", "AYT",280);
    Ticket item5 = new Ticket(5, 10000, "SVO", "AER",240);
    Ticket item6 = new Ticket(6, 10500, "SVO", "AER",255);
    Ticket item7 = new Ticket(7, 9800, "SVO", "AER",248);
    Ticket item8 = new Ticket(8, 11000, "SVO", "AER",235);

    @Test
    public void shouldAddAndRemoveTicketsFromRepository () {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);
        manager.removeTicketById(item3.getId());

        Ticket[] expected = {item1, item2};
        Ticket[] actual = manager.getSavedTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldTryToRemoveTicketsFromRepositoryIfItemsNotPresent () {
        TicketManager manager = new TicketManager(new TicketRepository());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeTicketById(4);
        });
    }

    @Test
    public void shouldTryToAddTicketsToRepositoryIfItemsWithSimilarIdAlreadyPresent () {
        TicketManager manager = new TicketManager(new TicketRepository());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            manager.addNewTicketButCheckIfSimilarTicketAlreadyAdded(item3);
        });
    }

    @Test
    public void shouldFindTicketByTwoAirportsAndSortThem () {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);
        manager.addNewTicket(item4);
        manager.addNewTicket(item5);
        manager.addNewTicket(item6);
        manager.addNewTicket(item7);
        manager.addNewTicket(item8);

        Ticket[] expected = {item7, item5, item6, item8};
        Ticket[] actual = manager.findAll("SVO", "AER");
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindTicketByTwoAirportsAndSortThemIfNoItemsInRepository () {
        TicketManager manager = new TicketManager(new TicketRepository ());


        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("SVO", "AER");
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindTicketByTwoAirportsAndSortThemIfOneItemInRepository () {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item8);

        Ticket[] expected = {item8};
        Ticket[] actual = manager.findAll("SVO", "AER");
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketByTwoAirportsAndSortThemIfOneItemInRepositoryButDoesNotQualify () {
        TicketManager manager = new TicketManager(new TicketRepository ());
        manager.addNewTicket(item1);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("SVO", "AER");
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketByTwoAirportsAndSortThemByComparator () {
        TicketManager manager = new TicketManager(new TicketRepository ());
        TicketByTravelTimeComparator travelTimeComparator = new TicketByTravelTimeComparator();
        manager.addNewTicket(item1);
        manager.addNewTicket(item2);
        manager.addNewTicket(item3);
        manager.addNewTicket(item4);
        manager.addNewTicket(item5);
        manager.addNewTicket(item6);
        manager.addNewTicket(item7);
        manager.addNewTicket(item8);

        Ticket[] expected = {item8, item5, item7, item6};
        Ticket[] actual = manager.findAll("SVO", "AER");
                Arrays.sort(actual, travelTimeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

}
