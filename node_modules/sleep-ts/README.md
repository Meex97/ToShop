# sleep-ts [![npm](https://img.shields.io/npm/v/sleep-ts.svg)](https://www.npmjs.com/package/sleep-ts) [![npm](https://img.shields.io/npm/l/sleep-ts.svg)](https://github.com/dguo/sleep-ts/blob/master/LICENSE) [![Build Status](https://travis-ci.org/dguo/sleep-ts.svg?branch=master)](https://travis-ci.org/dguo/sleep-ts) [![codecov](https://codecov.io/gh/dguo/sleep-ts/branch/master/graph/badge.svg)](https://codecov.io/gh/dguo/sleep-ts) [![Known Vulnerabilities](https://snyk.io/test/github/dguo/sleep-ts/badge.svg?targetFile=package.json)](https://snyk.io/test/github/dguo/sleep-ts?targetFile=package.json)
Pause JavaScript execution for a specified amount of time using
[Promises](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise).
Works elegantly with
[async/await](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/async_function)
and accepts flexible time formats like `'3s'` and `'1h'` (any string that can
be parsed by the [ms package](https://github.com/zeit/ms)). Implemented in
[TypeScript](http://www.typescriptlang.org/).

## Installation
```sh
$ yarn add sleep-ts
# or
$ npm install --save sleep-ts
```

## Usage
```js
import {sleep} from 'sleep-ts';
// or
const sleep = require('sleep-ts').sleep;

// With async/await
(async function slowHelloWorld() {
    console.log('Hello');

    // a number argument is treated as milliseconds, so this will
    // sleep for 1 second
    await sleep(1000);

    // a string argument is parsed, so this will sleep for 2 seconds
    await sleep('2s');

    console.log('World');
})()

// With raw Promises
sleep(4000).then(() => {
    console.log('PING');

    // If you pass a second argument, the sleep Promise will
    // resolve with the given value.
    return sleep(3000, 'PONG');
}).then(response => {
    console.log(response);
});

```

Output:
```sh
Hello
# 3 seconds later
World
# 1 second later
PING
# 3 seconds later
PONG
```

## Alternatives
* If you don't care about the fancy time format parsing, you can use [sleep-promise](https://github.com/brummelte/sleep-promise), which might save you a subdependency on the `ms` package.
* Use [setTimeout](https://developer.mozilla.org/en-US/docs/Web/API/WindowOrWorkerGlobalScope/setTimeout) if you're okay with using callbacks.

## Other Languages
* C#: [Thread.Sleep](https://docs.microsoft.com/en-us/dotnet/api/system.threading.thread.sleep)
* C++: [std::this_thread::sleep_for](http://www.cplusplus.com/reference/thread/this_thread/sleep_for/) (since C++11; otherwise, check out [these methods](http://www.martinbroadhurst.com/sleep-for-milliseconds-in-c.html))
* Go: [time.Sleep](https://golang.org/pkg/time/#Sleep)
* Java: [Thread.sleep](https://docs.oracle.com/javase/9/docs/api/java/lang/Thread.html#sleep-long-)
* Perl: [sleep](http://perldoc.perl.org/functions/sleep.html)
* PHP: [sleep](https://secure.php.net/manual/en/function.sleep.php)
* Python: [time.sleep](https://docs.python.org/library/time.html#time.sleep)
* R: [Sys.sleep](https://www.rdocumentation.org/packages/base/topics/Sys.sleep)
* Ruby: [sleep](https://ruby-doc.org/core-2.5.0/Kernel.html#method-i-sleep)
* Rust: [std::thread::sleep](https://doc.rust-lang.org/std/thread/fn.sleep.html)

## License
[MIT](https://github.com/dguo/sleep-ts/blob/master/LICENSE)
