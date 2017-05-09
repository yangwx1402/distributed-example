package com.young.distributed.core.task.leader

/**
  * Created by yangyong3 on 2017/5/9.
  */
trait DLeaderLatchTask {

  def runTask(is_stay:Boolean)
}
