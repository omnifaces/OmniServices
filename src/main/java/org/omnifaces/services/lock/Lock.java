/*
 * Copyright 2021, 2023 OmniFaces
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.omnifaces.services.lock;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.omnifaces.services.lock.Lock.TimeoutType.TIMEOUT;
import static org.omnifaces.services.lock.Lock.Type.WRITE;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.enterprise.util.Nonbinding;
import jakarta.interceptor.InterceptorBinding;

@InterceptorBinding
@Target({ METHOD, TYPE })
@Retention(RUNTIME)
@Inherited
public @interface Lock {

    enum Type {
        /**
         * For read-only operations. Allows simultaneous access to methods designated as <code>READ</code>, as long as no
         * <code>WRITE</code> lock is held.
         */
        READ,

        /**
         * For exclusive access to the bean instance. A <code>WRITE</code> lock can only be acquired when no other method with
         * either a <code>READ</code> or <code>WRITE</code> lock is currently held.
         */
        WRITE
    }

    enum TimeoutType {
        /**
         * A timeout value in the units specified by the <code>unit</code> element.
         */
        TIMEOUT,

        /**
         * Concurrent access is not permitted; so no wait therefore no timeout is allowed.
         * Either the lock is available and grabbed immediately, or an exception is thrown.
         */
        NOT_PERMITTED,

        /**
         * The client request will block indefinitely until it can proceed; it waits for as long
         * as it needs to, hence the timeout is unlimited.
         */
        INDEFINITTE
    }

    /**
     *
     * @return The Lock.Type to use
     */
    @Nonbinding Type type() default WRITE;

    /**
     *
     * @return the way to deal with time out waiting for a lock
     */
    @Nonbinding TimeoutType timeoutType() default TimeoutType.TIMEOUT;

    /**
     *
     * @return the time to wait to obtain a lock
     */
    @Nonbinding long accessTimeout() default 60;

    /**
     *
     * @return units used for the specified accessTimeout value.
     */
    @Nonbinding TimeUnit unit() default SECONDS;


    /**
     * Supports inline instantiation of the {@link Lock} annotation.
     *
     */
    public static final class Literal extends AnnotationLiteral<Lock> implements Lock {

        private static final long serialVersionUID = 1L;

        /**
         * Instance of the Literal annotation.
         */
        public static final Literal INSTANCE = of(WRITE, TIMEOUT, 60, SECONDS);

        private final Type type;
        private final TimeoutType timeoutType;
        private final long accessTimeout;
        private final TimeUnit unit;

        public static Literal of(Type type, TimeoutType timeoutType, long accessTimeout, TimeUnit unit) {
            return new Literal(type, timeoutType, accessTimeout, unit);
        }

        private Literal(Type type, TimeoutType timeoutType, long accessTimeout, TimeUnit unit) {
            this.type = type;
            this.timeoutType = timeoutType;
            this.accessTimeout = accessTimeout;
            this.unit = unit;
        }

        @Override
        public Type type() {
            return type;
        }

        @Override
        public TimeoutType timeoutType() {
            return timeoutType;
        }

        @Override
        public long accessTimeout() {
            return accessTimeout;
        }

        @Override
        public TimeUnit unit() {
            return unit;
        }
    }


}
