package xyz.ivyxjc.tinykspring.aop

import org.aopalliance.intercept.MethodInvocation
import java.lang.reflect.AccessibleObject
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface AopProxy {
    fun getProxy(): Any
}

class JdkDynamicAopProxy(val advised: AdvisedSupport) : AopProxy, InvocationHandler {

    override fun getProxy(): Any {
        return Proxy.newProxyInstance(javaClass.classLoader, arrayOf(advised.targetSource?.targetClass!!), this)
    }

    override fun invoke(proxy: Any?, method: Method, args: Array<Any>?): Any? {
        val methodInterceptor = advised.methodInterceptor
        return methodInterceptor!!.invoke(ReflectiveMethodInvocation(advised.targetSource?.target!!, method, args))
    }
}


class ReflectiveMethodInvocation(private val target: Any, private val method: Method, private val args: Array<Any>?) : MethodInvocation {

    override fun getMethod(): Method {
        return method
    }

    override fun getArguments(): Array<Any>? {
        return args
    }

    override fun proceed(): Any? {
        if (args == null) {
            return method.invoke(target)
        } else {
            return method.invoke(target, *args)
        }
    }

    override fun getThis(): Any {
        return target
    }

    override fun getStaticPart(): AccessibleObject {
        return method
    }
}
