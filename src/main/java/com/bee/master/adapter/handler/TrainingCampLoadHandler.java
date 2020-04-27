package com.bee.master.adapter.handler;

import com.bee.master.application.service.TrainingCampReadService;
import com.bee.master.application.vo.TrainingCampVO;
import com.gaia.collaborator.command.Command;
import com.gaia.collaborator.command.CommandHandler;
import com.gaia.collaborator.event.Event;
import lombok.AllArgsConstructor;

import java.util.List;
import static com.google.common.collect.ImmutableList.of;

@AllArgsConstructor
public class TrainingCampLoadHandler implements CommandHandler {

    private final TrainingCampReadService trainingCampReadService;
    @Override
    public boolean accept(Command command) {
        return command.isType("campLoad");
    }

    @Override
    public List<Event> handle(Command command) {
        List<TrainingCampVO> camps = trainingCampReadService.getRelatedTrainingCamps(command.getCreatedBy());
        return of(Event.from(command)
                .payload(camps)
                .sendTo(of(command.getCreatedBy())));
    }
}
