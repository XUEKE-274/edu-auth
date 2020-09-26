package com.shengrong.chemicalsystem.component;

import com.shengrong.chemicalsystem.ecxeption.CoreException;

public interface ValidateComponent {
    <T> void validate(T t, Class<?>... groups) throws CoreException;
}
