package org.example.frameworkstudy.service;

public interface ViewCountService {

    void incrementViewCount(int boardId);

    int getViewCount(int boardId);
}
