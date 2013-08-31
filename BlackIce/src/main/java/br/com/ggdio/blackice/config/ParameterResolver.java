package br.com.ggdio.blackice.config;

/**
 * Interface for parameter resolve
 * @author rm69232
 *
 * @param <T>
 */
public interface ParameterResolver<T> {
	public BlackiceParameters resolveParameters(T arg);
}
