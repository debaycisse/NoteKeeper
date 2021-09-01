package com.gads2021_pluralsight.notekeeper

// class to represent pluralsight or yabatech courses
class CourseInfo(val id: String, val title: String)   // Since this class doesn't need body, there's no need to add opening and closing braces
{
    override fun toString(): String {
        return title
    }
}

// class to represent note
class NoteInfo(var course: CourseInfo, var title: String, var text: String)

// class to server as central point of management
