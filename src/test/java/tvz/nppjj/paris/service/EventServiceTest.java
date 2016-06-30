package tvz.nppjj.paris.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.verification.TooLittleActualInvocations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.model.Category;
import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.model.exception.ParisException;
import tvz.nppjj.paris.repository.EventRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
public class EventServiceTest {

    @Mock
    EventRepository      eventRepository;

    @Mock
    CategoryService      categoryService;

    @InjectMocks
    private EventService eventService;

    @Before
    public void setup() {
        eventService = new EventServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public final void testSaveEvent() throws ParseException {

        EventCommand eventCommand = createEventCommand();
        when(categoryService.getCategoryById(anyLong())).thenReturn(Mockito.mock(Category.class));
        when(eventRepository.save(any(Event.class))).thenAnswer(new Answer<Event>() {

            @Override
            public Event answer(InvocationOnMock invocation) throws Throwable {
                Event event = invocation.getArgument(0);
                assertThat(event.getCity()).isEqualTo(eventCommand.getCity());
                assertThat(event.getDate()).isEqualTo(eventCommand.getDate());
                assertThat(event.getName()).isEqualTo(eventCommand.getName());
                assertThat(event.getLocation()).isEqualTo(eventCommand.getLocation());
                assertThat(event.getPicture()).isEqualTo(eventCommand.getPicture());
                assertThat(event.getPrice()).isEqualTo(eventCommand.getPrice());
                assertThat(event.getDescription()).isEqualTo(eventCommand.getDescription());
                assertThat(event.getCategory()).isNotNull();
                return event;
            }
        });

        eventService.saveEvent(eventCommand);

        verify(eventRepository, times(1)).save(any(Event.class));

    }

    @Test(expected = ParisException.class)
    public final void testSaveEvent_whenIdDoesNotExistInDatabase_thenExceptionIsThrown() throws ParseException {

        EventCommand eventCommand = createEventCommand();
        eventCommand.setId(213L);
        when(categoryService.getCategoryById(anyLong())).thenReturn(Mockito.mock(Category.class));
        when(eventRepository.findOne(anyLong())).thenReturn(null);

        eventService.saveEvent(eventCommand);

        verify(eventRepository, times(0)).save(any(Event.class));

    }

    @Test(expected = TooLittleActualInvocations.class)
    // expect test to fail
    public final void testSaveEvent_whenValidEventCommand_thenSaveShouldBeCalledOnlyOnce() throws ParseException {

        EventCommand eventCommand = createEventCommand();

        eventService.saveEvent(eventCommand);

        verify(eventRepository, times(2)).save(any(Event.class));

    }

    private EventCommand createEventCommand() throws ParseException {

        @SuppressWarnings("deprecation")
        Date date = new Date(2016, 1, 12);

        BigDecimal price = new BigDecimal("456.23");

        EventCommand command = new EventCommand();

        command.setName("kjfdnkjdn");
        command.setDate(date);
        command.setCity("dcsdcsaa");
        command.setDescription("kdsnmsdkjn asknmjx asnmxn");
        command.setLocation("dsncjndc");
        command.setPicture("//");
        command.setPrice(price);
        command.setIdCategory(2L);

        return command;
    }

    // private Category createMockCategory() {
    // Category category = new Category();
    //
    // category.setId();
    // return null;
    // }

}
