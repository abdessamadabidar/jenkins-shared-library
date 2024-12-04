#!user/bin/env groovy

def call() {
    return new Maven(this).build()
}