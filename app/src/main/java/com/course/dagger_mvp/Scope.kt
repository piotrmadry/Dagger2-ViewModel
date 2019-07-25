package com.course.dagger_mvp

import javax.inject.Scope

class Scope {
    @Scope
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Activity

    @Scope
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Fragment

    @Scope
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NestedFragment
}