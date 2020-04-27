package com.bee.master.adapter.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailMessage {

    private String receipt;

    private String subject;

    private String body;
}
