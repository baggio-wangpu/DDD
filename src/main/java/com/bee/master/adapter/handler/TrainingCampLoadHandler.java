package com.bee.master.adapter.handler;

import com.bee.master.application.service.TeacherService;
import com.bee.master.application.vo.TrainingCampVO;
import com.gaia.collaborator.command.Command;
import com.gaia.collaborator.command.CommandHandler;
import com.gaia.collaborator.event.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.google.common.collect.ImmutableList.of;

@AllArgsConstructor
@Component
public class TrainingCampLoadHandler implements CommandHandler {

    private final TeacherService teacherService;
    @Override
    public boolean accept(Command command) {
        return command.isType("trainingCampLoad");
    }

    @Override
    public List<Event> handle(Command command) {
        List<TrainingCampVO> camps = teacherService.getTrainingCampsByTeacher(command.getCreatedBy());
        return of(Event.from(command)
                .payload(camps)
                .sendTo(of(command.getCreatedBy())));
    }
}
