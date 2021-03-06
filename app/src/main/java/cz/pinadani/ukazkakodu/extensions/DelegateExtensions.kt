package cz.pinadani.ukazkakodu.extensions


fun <T> lazyN(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)