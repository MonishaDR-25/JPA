package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.NotificationDto;
import com.xworkz.DairyManagement.entity.ProductCollectionEntity;
import com.xworkz.DairyManagement.repository.ProductCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductCollectionNotificationService {

    @Autowired
    private ProductCollectionRepository productCollectionRepository;

    public List<NotificationDto> generateNotifications() {

        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();

        List<NotificationDto> notifications = new ArrayList<>();

        // Mid-month notification (1–15)
        if (day == 16 || day == 17 || day == 18) {

            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = LocalDate.of(year, month, 15);

            notifications.addAll(
                    buildNotifications(start, end, "1–15 " + today.getMonth(), "16th")
            );
        }

        // End-of-month notification (16–month end)
        if (day == 1 || day == 2 || day == 3) {

            LocalDate prevMonth = today.minusMonths(1);

            LocalDate start = LocalDate.of(prevMonth.getYear(), prevMonth.getMonthValue(), 16);
            LocalDate end = prevMonth.withDayOfMonth(prevMonth.lengthOfMonth());

            notifications.addAll(
                    buildNotifications(start, end, "16–" + end.getDayOfMonth() + " " + prevMonth.getMonth(), "1st")
            );
        }

        return notifications;
    }

    private List<NotificationDto> buildNotifications(LocalDate start, LocalDate end,
                                                     String period, String showDate) {

        List<ProductCollectionEntity> list =
                productCollectionRepository.findBetweenDates(start, end);

        Map<Integer, NotificationDto> map = new HashMap<>();

        for (ProductCollectionEntity pc : list) {

            int agentId = pc.getAgent().getAgentId();
            String agentName = pc.getAgent().getFirstName() + " " + pc.getAgent().getLastName();

            map.putIfAbsent(agentId, new NotificationDto());

            NotificationDto dto = map.get(agentId);

            dto.setAgentId(agentId);
            dto.setAgentName(agentName);
            dto.setPeriod(period);

            dto.setTotalAmount(
                    dto.getTotalAmount() == null ? pc.getTotalAmount() :
                            dto.getTotalAmount() + pc.getTotalAmount()
            );

            dto.setTotalQuantity(
                    dto.getTotalQuantity() == null ? pc.getQuantity() :
                            dto.getTotalQuantity() + pc.getQuantity()
            );

            dto.setMessage(
                    "Agent " + agentName +
                            " collected " + dto.getTotalQuantity() +
                            " liters (₹" + dto.getTotalAmount() + ") during " + period
            );

            dto.setOverdue(false); // not required here
            dto.setDueDate(showDate);
        }

        return new ArrayList<>(map.values());
    }
}
