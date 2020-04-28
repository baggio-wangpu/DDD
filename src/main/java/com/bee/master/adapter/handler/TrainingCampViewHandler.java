package com.bee.master.adapter.handler;

import com.bee.master.application.service.TrainingCampReadService;
import com.bee.master.application.vo.TrainingCampVO;
import com.gaia.collaborator.command.Command;
import com.gaia.collaborator.command.CommandHandler;
import com.gaia.collaborator.event.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@Component
@AllArgsConstructor
public class TrainingCampViewHandler implements CommandHandler {

    private final TrainingCampReadService trainingCampReadService;

    @Override
    public boolean accept(Command command) {
        return command.isType("trainingCampView");
    }

    @Override
    public List<Event> handle(Command command) {
        Long trainingCampId = command.getPayload().getLong("id");
        TrainingCampVO trainingCampVO = trainingCampReadService.get(trainingCampId);
        return of(Event.from(command)
                .payload(trainingCampVO)
                .sendTo(of(command.getCreatedBy())));
    }
}
