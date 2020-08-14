package com.bee.master.adapter.handler.trainingCamp;

import com.bee.master.application.request.trainingCamp.CreateTrainingCampRequest;
import com.bee.master.application.service.trainingCamp.TrainingCampWriteService;
import com.bee.master.application.vo.TrainingCampVO;
import com.gaia.collaborator.Payload;
import com.gaia.collaborator.command.Command;
import com.gaia.collaborator.command.CommandHandler;
import com.gaia.collaborator.event.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@Component
@AllArgsConstructor
public class TrainingCampCreateHandler implements CommandHandler {

    private final TrainingCampWriteService trainingCampWriteService;

    @Override
    public boolean accept(Command command) {
        return command.isType("trainingCampCreate");
    }

    @Override
    public List<Event> handle(Command command) {
        CreateTrainingCampRequest request = Payload.of(command).to(CreateTrainingCampRequest.class);
        TrainingCampVO trainingCampVO = trainingCampWriteService.create(request, command.getCreatedBy());
        return of(Event.from(command)
                .payload(trainingCampVO)
                .sendTo(of(command.getCreatedBy())));
    }
}
