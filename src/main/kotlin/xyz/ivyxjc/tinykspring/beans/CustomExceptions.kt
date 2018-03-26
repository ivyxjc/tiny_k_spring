package xyz.ivyxjc.tinykspring.beans


class BeanNotfoundException(override val message: String) : Throwable()

class BeanDefinitionNotfoundException(override val message: String) : Throwable()