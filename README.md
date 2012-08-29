Scooter [![Build Status](https://secure.travis-ci.org/mongoid/scooter.png?branch=master&.png)](http://travis-ci.org/mongoid/scooter)
=======

Scooter is an asynchronous (non-blocking) MongoDB driver for the JVM,
written in Scala.

What is it?
-----------
Outside of being an async driver, Scooter is also an implementation of
the MongoDB Wire Protocol and the BSON Specification that is designed in
proper OO fashion and is performant. Scooter, unlike other JVM drivers
does not wrap the MongoDB Java driver, and thusly has a different API and
feature set. The goal is to be the easiest to use and fastest MongoDB
driver on the JVM.

What is it not?
---------------
- A DSL for creating MongoDB queries outside of the normal hash-syntax.
- An Object Document Mapper (ODM)

Dependencies
------------
- A JVM higher than version 7 (OpenJDK or Oracle)
- Netty 4

Road Map
--------
Since Scooter has a dependency on Scala 2.10 and Netty 4, those must first
be released before this goes to production. Our hope is late 2012/early 2013.

License
-------

Copyright (c) 2012 Durran Jordan

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Credits
-------

Durran Jordan: durran at gmail dot com
