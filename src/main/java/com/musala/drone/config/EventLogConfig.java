package com.musala.drone.config;

import com.musala.drone.service.DroneService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Configuration
@EnableScheduling
public class EventLogConfig {
    @Autowired
    DroneService droneService;

    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedDelayTask() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(Calendar.getInstance().getTime());
        LoggerFactory.getLogger(getClass()).info(timeStamp);
        droneService.findAll().stream().forEach(drone -> {

            LoggerFactory.getLogger(getClass()).info("Battery capacity for drone SERIAL : {} is {}%",drone.getSerialNumber(),drone.getBatteryCapacity());
        });


    }
}
