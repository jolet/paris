package tvz.nppjj.paris.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.EventMock;

/**
 * Example service mock.
 * 
 * @author josip.kovacek
 *
 */
@Service
public class EventServiceMockImpl implements EventServiceMock {

	@Override
	public List<EventMock> getAllEvents() {
		List<EventMock> eventMockList = new ArrayList<>();
		int eventCount = 5;
		for (long i = 0; i < eventCount; i++) {
			EventMock eventMock = new EventMock();
			eventMock.setId(i);
			eventMock.setName("Event #" + i);
			eventMock.setLocation("Location #" + i);

			eventMockList.add(eventMock);
		}
		return eventMockList;
	}

}
