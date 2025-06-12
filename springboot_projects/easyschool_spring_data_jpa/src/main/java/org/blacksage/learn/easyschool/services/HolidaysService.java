package org.blacksage.learn.easyschool.services;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschool.models.Holiday;
import org.blacksage.learn.easyschool.repositories.HolidaysRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class HolidaysService {

        private final HolidaysRepository holidaysRepository;

        public List<Holiday> fetchAllHolidays() {
                Iterable<Holiday> holidaysItr = holidaysRepository.findAll();
                return StreamSupport
                        .stream(holidaysItr.spliterator(), false)
                        .collect(Collectors.toList());
        }
}
