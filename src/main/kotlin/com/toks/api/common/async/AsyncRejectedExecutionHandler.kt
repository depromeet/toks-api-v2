package com.toks.api.common.async

import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor

class AsyncRejectedExecutionHandler : RejectedExecutionHandler {
    override fun rejectedExecution(runnable: Runnable, threadPoolExecutor: ThreadPoolExecutor) {}
}
