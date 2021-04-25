package com.cqx.stn.jagent;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class AttachAPI {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        final List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            System.out.println(virtualMachineDescriptor.id());
        }
        final VirtualMachine attach = VirtualMachine.attach("28972");
        attach.loadAgent("F:\\Projects\\somethingnew\\stn-javaagent\\src\\main\\java\\com\\cqx\\test\\cqx-javaagent.jar");
    }
}
