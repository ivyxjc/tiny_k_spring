package xyz.ivyxjc.tinykspring.beans


class BeanNotfoundException(override val message: String) : Throwable()

class BeanDefinitionNotFoundException(override val message: String) : Throwable()