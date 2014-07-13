package com.github.girichards

import org.specs2.mutable.Specification

import spray.testkit.Specs2RouteTest


class HelloWorldServiceSpec extends Specification with Specs2RouteTest {

  
//  new HelloWorldService
  
  
  
  
  "The 'Hello world' string" should  {
    
    
    "contain 11 characters" in  {
      
      "Hello world" must have size (11)
    }
    
    
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
  }
}