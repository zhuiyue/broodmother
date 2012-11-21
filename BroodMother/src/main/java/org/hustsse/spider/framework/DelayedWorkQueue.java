package org.hustsse.spider.framework;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedWorkQueue implements Delayed {

	WorkQueue wq;
	// 唤醒时刻,nanoSecond
	long wakeTime;

	/**
	 *
	 * @param wq
	 *            work queue
	 * @param delay
	 *            多久之后被唤醒，单位nanoSecond
	 */
	public DelayedWorkQueue(WorkQueue wq, long delay) {
		this.wq = wq;
		this.wakeTime = System.nanoTime() + delay;
	}

	@Override
	public int compareTo(Delayed o) {
		if (o == this)
			return 0;
		DelayedWorkQueue other = (DelayedWorkQueue) o;
		long d = wakeTime - other.getWakeTime();
		return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(wakeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	public WorkQueue getWorkQueue() {
		return wq;
	}

	public long getWakeTime() {
		return wakeTime;
	}

}
