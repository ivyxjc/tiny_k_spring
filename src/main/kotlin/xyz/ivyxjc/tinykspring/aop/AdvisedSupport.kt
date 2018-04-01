package xyz.ivyxjc.tinykspring.aop

import org.aopalliance.intercept.MethodInterceptor

class AdvisedSupport {

    var targetSource: TargetSource? = null

    var methodInterceptor: MethodInterceptor? = null
}

class TargetSource(val target: Any, val targetClass: Class<*>)

