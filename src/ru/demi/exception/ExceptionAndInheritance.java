package ru.demi.exception;

class ExceptionAndInheritance {

    void methodWithoutException() {}

    // we usually don't declare unchecked exception, it's for clarity
    void methodWithUncheckedException() throws RuntimeException {}

    void methodWithCheckedException() throws Exception {}

    // you can get rid of exception declaration in overridden method
    static class OverrideWithoutExceptions extends ExceptionAndInheritance {

        @Override
        void methodWithUncheckedException() {
        }

        @Override
        void methodWithCheckedException() {
        }
    }

    // you can weaken your exception declaration in overridden method
    static class OverrideWithUncheckedExceptions extends ExceptionAndInheritance {

        @Override
        void methodWithoutException() throws RuntimeException {
        }

        @Override
        void methodWithUncheckedException() throws RuntimeException {
        }

        @Override
        void methodWithCheckedException() throws RuntimeException {
        }
    }

    // we cannot make stricter exception declaration in overridden method
    static class OverrideWithCheckedException extends ExceptionAndInheritance {

        // we have compilation error: overridden method does not throw java.lang.Exception
//        @Override
//        void methodWithoutException() throws Exception {
//        }

        @Override
        void methodWithCheckedException() throws Exception {
        }

        // we have compilation error: overridden method does not throw java.lang.Exception
//        @Override
//        void methodWithUncheckedException() throws Exception {
//        }
    }
}
