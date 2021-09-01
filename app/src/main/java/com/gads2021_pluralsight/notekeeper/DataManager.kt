package com.gads2021_pluralsight.notekeeper

// this class requires properties to hold collection of courses and notes
class DataManager {
    // this is to hold courses, so that any course can be looked up via courseId
    // HashMap takes 2 data types;
    // 1st is for the lookup value, we're looking up course via courseId which is string type.
    // The 2nd type is the type that will be stored, e.g for our course, the type will be CourseInfo
    val courses = HashMap<String, CourseInfo>()   // this maps courseId to an instance of CourseInfo class

    // property to hold our collection of note
    // since we want to make it an assign-once (immutable) property, we are using 'val'
    // since we do not need any value-based lookup, we'll use arrayList to collect notes
    val notes = ArrayList<NoteInfo>()   // the type of what we're storing is NoteInfo


    // initializer block
    // Anytime, an instance of DataManager class is called, the code inside
    // init{} code block will get executed immediately.
    // with function we're calling inside init block, we're populating our courses collection with 4 courses
    init {
        initializeCourses()
    }

    // Let's start initializing our dataManager class with data
    // function to initialize our dataManager class
    // Because we don't want the below function, to be called-able outside DataManager class, hence we added 'private'
    private fun initializeCourses(){
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        // Once course instance has been created, we can add the instance to our hashMap collection
        // this can be done through the set method or function hashMap variable - 'courses'
        courses.set(course.id, course)   // the value of the first parameter of the set method is the value with which we will lookup the course, which is the courseId
        // while the second parameter will be the course itself

        // using named parameter style
        course = CourseInfo(id = "android_async", title = "Android Async Programming and Services")
        // Now, let's add the created course to our courses collection.
        courses.set(course.id, course)

        // Just we are aware, that when using namedParameter, the order of the parameters' values do not matter.
        course = CourseInfo(title = "Java Fundamentals : The Java Language", id = "java_lang")
        courses.set(course.id, course)  // adding the course to our collections

        // Let's add another course, using positional parameters' values assignment
        course = CourseInfo("java_core", "Java Fundamentals : The Core Platform")
        // Let's add this course also to our courses collection
        courses.set(course.id, course)

    }
}