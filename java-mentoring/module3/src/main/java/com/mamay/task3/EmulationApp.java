package com.mamay.task3;

import com.mamay.task3.entity.Emulation;
import com.mamay.task3.service.EmulationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

import static java.lang.System.out;

@Log4j2
public class EmulationApp {

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("task3-applicationContext.xml");
             Scanner scanner = new Scanner(System.in)) {

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
            log.error("Something wrong happened during bean instantiation", e);
        } catch (InterruptedException e) {
            log.error("Thread is interrupted", e);
        }
    }
}
