package at.kitsoft.redicraft.api;

import java.lang.management.ManagementFactory;

public class PerformanceMonitor {
	
	static long lastSystemTime = 0;
	static long lastProcessCpuTime = 0;
	static int availableProcessors = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
	
	public synchronized double getCpuUsage() {
		if(lastSystemTime == 0) {
			baselineCounters();
		}
		long systemTime = System.nanoTime();
		long processCpuTime = 0;
		
		if(ManagementFactory.getOperatingSystemMXBean() instanceof com.sun.management.OperatingSystemMXBean) {
			processCpuTime = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuTime();
		}
		double cpuUsage = (double) (processCpuTime - lastProcessCpuTime) / (systemTime - lastSystemTime)*100.0;
		lastSystemTime = systemTime;
		lastProcessCpuTime = processCpuTime;
		return cpuUsage / availableProcessors;
	}
	private void baselineCounters() {
		lastSystemTime = System.nanoTime();
		if(ManagementFactory.getOperatingSystemMXBean() instanceof com.sun.management.OperatingSystemMXBean) {
			lastProcessCpuTime = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuTime();
		}
	}

}
