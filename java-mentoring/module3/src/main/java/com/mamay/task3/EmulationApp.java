package com.mamay.task3;

import com.mamay.task3.service.Emulation;
import com.mamay.task3.service.EmulationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

import static java.lang.System.out;

public class EmulationApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmulationApp.class);

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("task3-applicationContext.xml");

        try (Scanner scanner = new Scanner(System.in)) {

            EmulationService emulationService = context.getBean("emulationService", EmulationService.class);

            while (true) {
                emulationService.showAvailableRaces();
                out.print("\nPlease select race by ID: ");
                Long raceId = scanner.nextLong();

                if (emulationService.existsRace(raceId)) {
                    Long horseId = null;
                    while (true) {
                        emulationService.showAvailableHorsesInRace(raceId);
                        out.print("\nPlease select horse by ID: ");
                        horseId = scanner.nextLong();
                        if (emulationService.existsHorseInRace(raceId, horseId)) {
                            break;
                        }
                        out.println("There is no horse with such id. Select another one\n");
                    }

                    Emulation emulation = new Emulation(raceId, horseId);
                    emulationService.addEmulation(emulation);
                    emulationService.start();
                } else {
                    out.println("There is no race with such id. Select another one");
                }

                emulationService.setRaceDone(raceId);
                if (!emulationService.moreRacesExist()) {
                    out.println("Currently we don't have available races");
                    break;
                }
                out.print("Print yes if you want to continue: ");
                String str = scanner.next();
                if (!"yes".equalsIgnoreCase(str)) {
                    break;
                }
            }

        } catch (BeansException e) {
            LOGGER.error("Something wrong happened during bean instantiation", e);
        } catch (InterruptedException e) {
            LOGGER.error("Thread is interrupted", e);
        } finally {
            context.close();
        }
    }
}
