## Tech History


### Deserializing Json into Scala Case Classes

This was an unexpected PITA. There seems to be no perfect solution, with "perfect" being a json deserialization
solution that can handle case classes easily while obeying parameters with defaults. I'd love to use play-json but
the model I get from POE has many more parameters than 22, which causes play json to freak out. 

I initially saw some posts on SO talking about using a library called Slickless that does something with lists to work 
around the problem but I avoided it because I didn't understand it. After trying straight up Jackson and some twitter 
thing called Finatra and being unsatisfied with both (Jackson doesn't know about scala defaults so it will actually set 
things to null, I never got Finatra to do anything other than mimic the core Jackson behavior and there's no 
documentation) I'm gonna try out Slickless.

Just kidding I'm not gonna try it yet, there is a play-json extensions library that I'm going to try first. The extensions
seem to be working except for one issue; an Item in POE references itself, as an Item can have socketed items. This confuses
the json library as it encounters the item type when trying to build the format for the item type, and it hasn't 
finished building it yet. Maybe we can leave that field as a linked list or whatever it naturally deserializes to, then
format it later? The recursion issue might have something to do with the format being implicit.

Instead of using jackson annotations to get the oddly formatted json into a format that follows scala conventions, I 
think I'm just going to write extension functions that basically "wrap" the poorly named fields with better names. That
will be a lot faster and less work than trying to get play json macros to obey jackson annotations, which probably 
isn't even possible.

### 
