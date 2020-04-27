package com.bee.master.adapter.handler;

import com.bee.master.application.vo.CampVO;
import com.gaia.collaborator.command.Command;
import com.gaia.collaborator.command.CommandHandler;
import com.gaia.collaborator.event.Event;

import java.util.ArrayList;
import java.util.List;
import static com.google.common.collect.ImmutableList.of;

public class CampLoadHandler implements CommandHandler {

    @Override
    public boolean accept(Command command) {
        return command.isType("camptLoad");
    }

    @Override
    public List<Event> handle(Command command) {
        List<CampVO> camps = new ArrayList<>();
        return of(Event.from(command)
                .payload(camps)
                .sendTo(of(command.getCreatedBy())));
    }
}
