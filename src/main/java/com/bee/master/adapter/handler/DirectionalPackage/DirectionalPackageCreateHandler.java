package com.bee.master.adapter.handler.DirectionalPackage;

import com.bee.master.application.request.trainingCamp.CreateDirectionalPackageRequest;
import com.bee.master.application.service.DirectionalPackage.DirectionalPackageWriteService;
import com.bee.master.application.vo.DirectionalPackageVO;
import com.gaia.collaborator.Payload;
import com.gaia.collaborator.command.Command;
import com.gaia.collaborator.command.CommandHandler;
import com.gaia.collaborator.event.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@AllArgsConstructor
@Component
public class DirectionalPackageCreateHandler implements CommandHandler {

    private final DirectionalPackageWriteService directionalPackageService;

    @Override
    public boolean accept(Command command) {
        return command.isType("directionalPackageCreate");
    }

    @Override
    public List<Event> handle(Command command) {
        CreateDirectionalPackageRequest request = Payload.of(command).to(CreateDirectionalPackageRequest.class);
        DirectionalPackageVO directionalPackage = directionalPackageService.createDirectionalPackage(request);
        return of(Event.from(command)
                .payload(directionalPackage)
                .sendTo(of(command.getCreatedBy())));
    }
}
