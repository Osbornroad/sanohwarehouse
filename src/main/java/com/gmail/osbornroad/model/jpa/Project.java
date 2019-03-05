package com.gmail.osbornroad.model.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Project {

    P32R,
    P32S,
    P42M;

    public static Comparator<Project> projectComparator = Comparator.comparing(obj -> obj.ordinal());

    public static List<Project> getProjectList() {
        List<Project> projectList = new ArrayList<>(Arrays.asList(Project.values()));
        projectList.sort(projectComparator);
        return projectList;
    }
}
