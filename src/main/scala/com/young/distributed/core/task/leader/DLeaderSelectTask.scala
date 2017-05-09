package com.young.distributed.core.task.leader

/**
  * Created by yangyong on 17-5-6.
  * 该接口的作用是实现了LeaderTask,也就是说master/slave架构中的master,多个实例可以实现master的高可用
  */
trait DLeaderSelectTask {

  @throws[DLeaderTaskException]
  def runTask(is_stay:Boolean)
}
