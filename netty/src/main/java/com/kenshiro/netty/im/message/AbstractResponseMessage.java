package com.kenshiro.netty.im.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractResponseMessage extends Message {

    private boolean success;
    private String reason;

}
