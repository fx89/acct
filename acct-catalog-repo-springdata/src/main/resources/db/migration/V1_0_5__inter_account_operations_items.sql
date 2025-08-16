-- Insert the Operations icon category
insert into "icon_category"(icon_category_name) values('Operations');

-- Insert the Operations category icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    'f83a0ae7-b9eb-45f9-8c05-f154026d5112',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Operations',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8AAAAAMyd88wAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAAd0SU1FB+kIEAwDAlHPNz8AAAMMSURBVHja7d3dbqMwEEBhm6B9aa556VUb78UmUrfdtIkx/oHvSL2KUghzsMdjg0MAAAAAAJyIOPLJp5SmqhcrxuvRBJgHDnxcluWt8nHnEEI6kghxwOBfagf+M+u6zjHGdwKcMPhHkyAOFPxpWZauLvi6rpfRu4OJrOdNoof5AT3e/UdpBaaAU0MAAoAAOC2zS1AuUc3KwhsnkARoPEpJKTUdRegC5AAgACSBPSZIH3MloRpUgJ7LuDAKKEKM8ZpSuhgGnlwCLQBezoNO1wKs6zqHENKrN1gvK4FK50GtC0EtWoCXF1XWXv2rDgACgAAgAAgAAoAAIAAIAALgWAwxGXSbbp17mw+4PSF8ffL8L48+I8BzpJHPqdfp4mG6gBjj9TaTONTdrwsoK8F7D11BzsshrAcoK8ElNFgjcF/LkDOdbT1A4e7gfvFaHNcooDMRoA4AAoAAIAAIAAKAACAAfsazgRUwHYxuq5YEqITZwJMHv9fZQEmgUQAIAAJAHQA/ZOxbRoHffZZzvFKJIwG+Br7qWsPcY5Xaw5AAH4ZjI72I6n6uKaVN29fNO91F3zV/X5q81sWQ0YL/WYQtEsQeLmTLTRiP8irb3N3LpsIX8m2Dxa1GJEd5EXXW7zj1MHCLtJ12BVMzAbY8u3eU5+xGpGgLEGN8f1WCI+3EfXoBXpVA8A8owLMSCP6BBEgpTZ//QghpXddfD4L/6+/X/vs9VGTeGviQUTpdluX3N/+zSIkTOwuwV/WsVIkTO3YBNUqnt3HtRYg6E6Bm8aRxhZAAj5L8yudoz8BeBGhROtUKDFgHAAFAABAABAABQAAQAAQAAUAAEAAEAAFAABAABAABMIIALTZv9OBofy1A7S1ckzB1JEDNVsDd32kOkPMYeGbwPRm0M9lB3GsL19ztWVFZgHt3EELZLVwFfiABBE0dAAQAAUAAEAAEAAEGosXk1l7kls21AMeZbEpagJO2Alsmzbx/5377DLppxNZJMwL8K0H1PYO2BD4UmDQjwGMRuu+6RAoAAAAAAADAc/wBcke+7SnIKskAAAAASUVORK5CYII='
);

-- Insert the Inter-account operations sub-category icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    '147b99f5-74a3-469a-b67d-589400a93e88',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Inter-account operations',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8AAAAAMyd88wAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAAd0SU1FB+kIEAwGBMXbZk8AAAHkSURBVHja7dzNTgMhFAZQmE58ada8tLHiRpNmEuu0ZjoXOCdxVxN6+Tr8tJASAAAAAAAAAAAAAAAAAAAAAAAAAIHkPS9qrS2HNiLnzxmLH6Gu644G5lLKx8GFWFNKbZYgRKprvvPPl6MbuFVrXXPO18E7P1Rdc5RGzhCCiHXNkRo5cgii1jVHa+SIIYhc13w7MSmlhCp4rfVy1sRwO0N/th3R67o+uiSMuEx9xSe2tfbsEyl0XXPUlJ71FLj3uH50WOqhrkti22G/jtWllI/W2mWk9ywADxotBAIweQgEYPIQCMBmqVdrXWcKQbcBaK0tR/yllFqt9W2WEKyddn6YnbWbEHS5c7no/LnnBEtnnb9E7PxNCBYBQAAQAATg3HX6q31/WfQpAMeG4BoxBL3+gKXLfYCc8/XgJdellPI+eud3G4Cf4eDAfYYpOt8k8J/7DCP8blEAJhvzBUDnC4DOF4Cn9hlGPLAiADv3GUY9srbq8n37DKOeXM6b9W+or1pH+NRFr+vtENAi1m+E7YXIbVr2ToBOSmn3j93odXU8fKKh4M/j4REa64KI19bVFTEThODhK2JuGvuSy4y+x0iXRJ1QV9fEnR8EdQUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJjJF3nIxObwEgEwAAAAAElFTkSuQmCC'
);

-- Insert the Income category icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    '6821b056-7d7e-4eef-a719-5a29dff6d0e3',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Income',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8AAAAAMyd88wAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAAd0SU1FB+kIEAwMCtiMo8IAAAL0SURBVHja7d3NdoIwEIbhTOD0prOem+6ppouaHotKVUgyZN5n15ZFYT7yR9AQAAAAAAAAAAAAAAAAAADACMT7Bcg5xz8XROTs6fxn58WfUkpfi9/NInKiBXBY/EJV3YRAnBY/ppRWC6yqk4fuIDIM8o0AEAAQALjlYhq4nOuzTuAoAPeme6r6wTqBg2ng2lz/P6r6kVL6HH2dQAYu/r9z/S1GWSdgEMgsAAQABGA0InJW1SqznMsgcIjp4PAPg7bMBEafAbgIwJ4hGPExsZvHwVtDMOoeAVf7Ad4NwcgbRNxtCHk1BKPvDvK6I+ipEHjYGsaeQMfFdx2AtRCwKdRXCG4Ww7y9GwAAAAAAcOMw6wBb9vZ3v8iG1xXmgxR/100dHf5/syuLQvHbsLq8LAYLHkuzWXtvf4cQTOW8rHQNs6WihxCk3O0552nEQdd1qHPOcwgh9wzDbKDwMkIT/47r8y5haB2EuWPxJ6+FXwtD6wFjpPj2gtCy+4sU33cIYuPiR4r/UgjiaC2AUFpb1yty9/tuBXg72LmWAaD5N3jdmgTgzeZfar7i3drVK+UvFbR2N2C2CyhTIRE5HT0E5UGQxSmw6Qt7CcHvxTtsG/7zAMjk+of5QWDrlbFKXaDZxa9DNK1MH5kGwnMLcG9QFX6eo8uzr3lbOt7NHHMxDTztWPib83j0lq+l4zecd7VPJT1aC3Bzl60UweLxjAE23v03A8Ly83KtwNrxBGCfu/+Vv1s7ngBUHq+I8eMJwB5rAdab+kfHMw3crxXIqjo9ebdZO55pIOsAtqaBhwyANzUDwFKwcwSAAIAAgACAAIAAgACAAIAAgACAAIAAgACAAGwz0lu+rdX+ouqWLUCmnPauG10AXUAbdAP2mv8eLQDdgLHr1TQAtAK27v4QOm1f5tNCnyp+k0203favE4L+xe8agEsIXH9c/LLwocPHxZt4g+XeF0Z4KnoZH/X4H8x+ZYwHfEs5AAAAAAAAAAAAAAAAAAAAdvENO/NEC7VP+b8AAAAASUVORK5CYII='
);

-- Insert the Passive income sub-category icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    '6f977843-99dd-442b-b5c3-811e6edf450c',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Passive income',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8AAAAAMyd88wAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAAd0SU1FB+kIEAwPE5fKWMEAAARiSURBVHja7Z3LkqQgEEVBif5p1/50R9nMYrTCqPIBApqQ5+4mxplu8x7JTEA0BiGEEEIIIaRLlhAcy3vfJQfZ2j+p9+ew+Nj8YRimDP9PLxWCDpt1CwBOhu5xHJNGyXEcHSmgbggm730fcGk/DMPvVgagCGy/VuiHYXjtPP0TAGA+AGA+AGA+AGA+AGA+AGA+AGA+AGA+AOwHvNgUdey07IH5P8aYKffPu1tOovk5VuCODA01Zf5dXlt/tzPtm/TznhCLQcoFAAUlfSVQZAoQVSBZ+xe4Elhl/hcJwCrodi//3v37NA258PYrNkX1R8VZC20b8wCR7RrmKwAA8xUDgPmKAcB8xQBgvmIAMF8xAJivGADMVwwA5isGAPMVA4D5igHAfIUArBaA7In5QVuyvm648Ore5wIW+wEigxe6HSx0S9bWqFLKlK0Ry3svepRiR1DhdDUMwytlUwkA1GF+d5SuZgg6AEDUAGcFmvfeldgKNh/14lvf4lU1ADMEU4mcifGVAIBZAFBF0dfKPAEAZGj35kmpKucJeDs4Q68fWIT+7E1ePTmlDQBxvX4xk8ZxfOQlUuYBlAsAAABpVlANUGoeu7Z+P6UIPMn/jxWB7qmbltACXQB2yj1V/fSmFvuU+esAmMrm6HPFRcKOJvtU2yMxGHdCIOV+xRSB0jdObKWDqx+TkAS7qC5A8saJXBBIG+loA2+EQGKa645atNTv5QCB/BqnK5XngKCOArf0RFAfs327hRc9tmIlucW1BQMR1Sbxlk9DAGC+YgAwXzEAmK8YAMxXDADmKwYA8xUDULP5Na05lJxXcErN7yUcRZ/w+2eLpcX8OpUrprZ185ehfn7z+NZNLjdA0C/3dTU1uBbN3zpnqKbNJrH3ukDtvXfGGB8Dg2vJ/Nl428IQf0Xr+15gOAPBNWR+r9X4IxjOCsYO89sH4Sj9uczm//z/Z/E99tXeFvODIdh8MF3OwF49u+9qb3t2Ohf6Hgk+H7ROSmAvbgvn9fbEeFW7LZynP0982RauXDUDwPCfIW5VAnBx+Lctveuw+jJ51IPwmQa6vZbsiUCV/Nz6qgqu/l2HZa4lRwvsDh6XqdSxrUc3dVc/XPPawPKF9RzeuJMfdFug7np5YoHAzIsmlabAbJNfTooxd7dD1IK0gchwVOy7/phTQtBSsrTr1fXSuXb2rAL9FZetwEu7PuG+32sC2keAr6fswASJ11MDJD79XwXh8ufPuQJp1wNAnqc/5u+lXQ8AhesfK/x6AMgxFyB9qN+7njYw3yjgx3HsA582adfTBjIPkNYGAoDODugNAFPBygUAAIAAAAEAAgAEAAgAEAAgAEAAgAAAAQACAAQAssQXza7r8wXcmkcAj53pcSMFkALqFGkgffhvYQQgDSTGq2oAGAXSnn5jGjloidNCg8zf3ETbzElbQBBvflMAzBCoPi7+03gTcFx8k2ftbX0wQpPpS30UVEc1X/ZW/HWwK0UxSQ8hFK5/G2RNqWRChjcAAAAASUVORK5CYII='
);

-- Insert the Transfer item icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    'f5e05aa5-c76d-4d01-8f80-5a4f4ee2ff58',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Transfer',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8AAAAAMyd88wAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAAd0SU1FB+kIDw4PN6A3L7cAAAQNSURBVHja7d3BbuIwGIVRO8lbe+23Bv7ZIBVVVKpGRJjec1aj2Yya6f0gSMStAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPBhqmqrqs2VaO1wCQgb/z7GuNz/fLTWqvd+S70e3a8ESa/8Y4zr97+fcx6992viNfE2iHhjjEtV7QIAIiAAIAICACIgACACAgAiIAAgAgIAIiAAIAICACIgACACAgAiIAAgAgIAIiAAIAICACIgACACAgAiIAAgAgIAIiAAIAICACIgACACAgAiIAAgAgIAIiAAIAICACLwn156OrAjl1lcXygCSxxI2l85/mcnrwLPrXAqsVdsCL4dEAAIjoAAQHAEBACCIyAAEBwBAYDgCAgABEdAACA4AgIAwREQAAiOgADAZ0RgEwBAAAABgAj3bw3eBAAyx3/aV4YFAELHLwAQPH4BgODxCwAEj18AIHj8AgDB4xcACB6/AEDw+AUAgscvABA8/tZefFSSo8FYXB9jXIz/y0vPBjzrCwvwV16gVhq/WwAIHr8AQPD4BQCCxy8AEDx+AYDg8QsABI9fACB4/AIAweMXAAgevwBA8PgFAILHLwAQPH4BgODxCwAEj18AIHj80QGoqs0DTEgef2svfiDIpwy/PTwZpqr+1H8oxi8AP49///5IqDHGpap2TzMibfxRtwDPxg/J448JgPFj/KEBMH6MPzQAxo/xhwbA+DH+0AAYP8YfGgDjx/hDA2D8GH9oAIwf4w8NgPHzG73325zzMP4vh/ETFoFrVe2PUUi+Hofxk/hOwFX48FsA44fQABg/hAbA+CE0AMYPoQEwfggNgPFDaACMH0IDYPwQGgDjh9AAGD+EBsD4ITQAxg+hATB+CA2A8UNoAIwfQgNg/PBeh/G31lrrjgpnZWc9xKQbP6xvznnKCdab8YPPAIwfBMD4QQCMHwTA+EEAjB8EwPhBAIwfBMD4QQCMHwSgtarajB98BgAIABATgN77bc55uKQQ+g6g934VAQi+BRABCP8MQAQgOAAiAOEBEAEID4AIQHgARADCAyACEB4AEYDwAIgAhAdABGAd/Z3/+CrPD7jHqPw6sOxQTzoa7HjzD3WtqmOBCNRZFxjcArgdAAEQARAAEQABEAEQABEAARABEAARAAEQAYgPgAhAeABEAMIDIAIQHgARgPAAiACEB0AEIDwAIgDhARABCA+ACEB4AEQAwgMgAhAeABGA8ACIAIQHQAQgPAAiAOEBEAEID4AIQHgARADCAyACEB6AnyIw5zycC0iqnvhDV9X2EAXjBwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPhY/wBkoPxRm2EkVQAAAABJRU5ErkJggg=='
);

-- Insert the Exchange item icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    '08de4d2e-309d-48e0-bc95-2a6ee91a3b78',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Exchange',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8AAAAAMyd88wAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAAd0SU1FB+kIEAs2C49tW6gAAAsbSURBVHja7d3dkuo2EIVR21DnoeNrXjp1GOUikFAcZrCNf1rq9VXlLsnMCO3du1vC7joAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHA8fYt/VCllWGVx+v7LFkHLnBsUfT+O4++V/p/nrusKM4AEkET033G5XJgBGEAg4W8u+ndmwAjAABIJnxGAARB+dUaw1lBU+9Me5wrEf4oq/Dv336+Ucu77/hpM4Ksa5+NglEFIAGmr/lFp4IXYQ63R47CUKTCAZqv+FCGskQaeBN/XuC7PpsAQGEDT4v/UBPY83jzaEJgBA2hS/HNNoHXRMwMGkE7870wgq+iZAQNII/5nEyB6ZsAAkon/YVP/Gsfxb9tumRkwgsYMIJP4o1TTT/fLkZ8XI2jIAIh/P5GvKZgfLh3tZg6MoHIDKKUM4zheLf96Yo8ghr0vKTGCzznqKnBv6ZcLPuqGf/V7lVJOWxnC4xVsRlCJEEX/+aJvaWNveQoiEQQ3ANE/p+j3NoO1rmFrAUT/PUT/q+u6axbRf9cyPLQKH5vBOI6/tQXBBKn6/2gCJxv1j2SwSirQFsRJAKq/tZmVDG6p4CMjOPpZDTae6i8FBJkVSAPHJQAVzhqtOStYZATSwAGbTvWXAiLOCZwU/MtgK6HWVND3/fUW6xfNBkoppzUfmKoFEG2t1f5GcF3aFmgJNt5w4r82YOe24KQliJcAgCrSQMbLQ1sbgEhrzXafDdzTwFwjyNgSbLbZxH9tQM1tQZaWwCkAmm8LlpwU3E8JtABA0tnAfS7QchKQAJBmNrAkDbSeBBgAtASJTYABgAkkNgEGACaQ2AQYAJhAYhNwCoD0JjD3hKCl0wEJAExgwQlBK0mAAQALW4KbCQwMAMg7F+gZAJDUBGpvBRgA8JrJb1Ou2QQYAPA6BXxlmAcwACDxPIABAInnAQwASGwCw4aL9rX0kc2ZuT2JxtOA6jeBIbUB3Ci2jjVLOhPoGQCQ1KBraQU2NQBtgPjfYAr4aqkV2OOXE2mtlVZACwDkM4HoKWDYYaG0AeJ/9sTWpzUA0dYaZZ8HRB4IDpEWSvVHq8YdtRUYoi2UTYQWU0DUVmAIulCqP2oygWoHgnv/MiqdNcn+OfZpDUAKUP2ztwLRUsBwwEJdmUCe109rBWKngKOcSOy1Bmk/10gp4JBfInsrIPprBaKkgOHAhUrZCoj+UkCkFHDoL5DNBIhfCoiWAoYAi3W9XC6/iB9SQEIDKKWcxnH8m/iRNTCk/eE38f8mfjQZASbu78vlcjpqKHw+aGGGbsbrmIkfrbYBR3M+QPzNV33iR9f9OwwspZwn7Pc+hQFkifxd1xXn/JiaAu7PCzhiz5x3Er7IDykgYwug6gOTZgF9cwbQuvgJH2txVBtw3kj4m0f+y+XSjeNI+NAGRDKAvar+OI7/CXGP+cLDz+oIH620Aecaxf/4I+9ifHjq6mpmQPRovQ04ryT8o6b8/WPMejSitaKbbYmW24DzCuI/bND3nWMSLrQB0xhqFT/QcBswhDaAUsoQSPy9bYNa2oBoz784LxB/qKp/5DVKYKM2IGYLIPIDbaXaYaLwh+Di1wbAHGCLFqCGqq8NQE1zgEjHgYPID+SdAwwNiV8bAKxhAKWUobbKH/X960DkgnaIYDI8BhyooaCddxL8eae+RxuA+KU90CDwvIfgn6fzWzmb0wBURIhB4HkNh3oneAAxOf8QU67fmcAegr9cLr8WvjFIGwCs0QLcTOD0KiHs8LtdL5fL7D5JGwCsOAM4Ukg/pRAAn3PUuXk/xwQWfIVSG4AW6Kv/Ac/cLhld3/T/f7xgY+7NxCNfuAhM1MLbPb31Po56c66slASA0B6QtQVYPBNgAkBSA2ACQHIDmGICtxmC/h9o0QB+MgFv6AWmU3WUfnVRSeUHkhgAwQNJWwAADAAAAwCQcgYAROD5ITe1zKYYAPC5+P+4019KqeI4WgsArCz+rvv/uRQMAGg49v/0bb4aTIABABsS/X0VDADYnp4BALlTwIkBNOCYwH+btO+/pn41/RsT6NMZwJRF854/VMTkp/o8msCU92/u8bX2IfqiAQ22A0MUnbgIBBzT4q4q7lemMiU9MADgw5Z27rsrxnH8vfQN2d+kh/7Vz59yG5EBAAe0tBNfe9c/Cb6fazTvTIABAIHnBSvNHL59t0DkSbujQGBjwhqAo0DUNAeo9VH1gwUDjpkDZE8A7gIAG/PuMpGIDTSaaqe8IyN6DDcIhDZggfC7ritTLgKFTgAGgcCyqj/1OwSHJYAlN6iA2SV5xQIS+UGfc6p+pBbAIBBbir5fs8CUUs73PftKaEcVtU/eh1nDUZw5AA4T/XNLOsEMyp7CX1L1qzKAd1cZQfhbin6OGey5R9d6C3Yf4AM8TXgwAgNACOFPqchb/l5rVP1oCWBKZNIGIKzwn1PB0q/67lX1oyWAYRzH64Q/Xgog/D7jqdHaVT9UAnAciDXaxJbFv+UrxqIMAbUBUPV3qvoRDWBSf+U0QNVX9Rs0AG0AiH+/qh81AWgDkFr8R7xOvKov2vhyEPG3/Ocf8UPDiGnG96mlAOJvsfp/pTaAqS4oBRA/krYAUgDxt8iRbw/ua90QbgYSf6OtwK6DwIgJwDMCcoh/IP7j29twBmAYmAafX4B1iToDMAwU/c0DshqAFCD6M4F9ilvkCioFiLjWKasBSAGqvxSwfXGLXj2npoCTLaOqWa8GPxD3Apqq/lcrMY+t93UN/fPUewGqi+pv3Vr8UGakgEO+UgnVv9YUUMsEfVIKcCqg+lu/Bg1g5quXbbZ41d/k/wO2LGzVVMu+769TTEAKABo0gDmtgBQg/lvHRj8cA8Hq4r/PYCW2GAbWGJXnDARdEAJaMoA5A0HzAPHferaXACYPBG3Cw+O/6f+KbFHQaq6OWgEgqwEsaAWYANBQApjVCjABoDEDYAJAcgOYMw9gAkCDBjDzuwKOB4HGEsDco8GuczwIdE1VQfMAILEBmAcAyQ1g4TyACYABZJ0H3E3AYBDZOLf6h/V9fy2lnKfeR7//e6UUXyOGBJAxCWgJwACYABMAA2ACTAAMgAkwATAAJuCEAC1yzvYHzz0duJtA1zkhgASQNgloCWav8deSNcb33J50/cUAApiAlmASxRLEXs/UDn1rB05d1/VLW4Ku64rXkqNaDViCm7VOfOHID9HMbGDldcX2e0yM/bAl0BZoA2peR0OaFVqC57ZAGoAWIHlLYDagDYge/yWA92ngvGTjGhJqA2pZPwngffUalrQErxLBzVi+kq6jFBCs+ksA05LA172aL93Aj/9d4lQgBQRcNwlg5zSQeU4gBcSq/hLA8jRw+tQInucESdoDKSDYekkAgSpahlmBFBCn+ksAnyeC6xpp4KdZQZJkgIPSkgQQcD7wUzKozRCebkf2qn+c6s8AKjOC7wwhiim8uApN8IHFrwXYpi1YbVA4pVV41TJ89zttJHJirzD6SwANJYIliWHpfiHydqo/A0hmBCB+BhCnT2YGOFT8DIAZILH4GQAzCCGA7v/5RLq//ehnRzAAZnCo6J9PKLLcFIzyGDkGUI8ZVGsIcy8ytW4CkZ4hyQDqNoRwprDWJaVWTSDaA2QZQJumsJlBvLtTsMGloybaoKhf/2YADGLehjlgA9eeBiI/Np4BoCYTqyoN1PDQFwYARpBQ+AwAjCCx8BkAGEFi4TMAtGgE3V5m0Mrj2xgAmEEy0QOpzODNP6e//vqr3F/u+tM/VhNoOzEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADswj+YEpAHS58uEgAAAABJRU5ErkJggg=='
);

-- Insert the Deposit item icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    '415be7b8-93c6-4fe7-9323-a7297e281e53',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Deposit',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAAuIwAALiMBeKU/dgAAAAd0SU1FB+kGFg8mH0In1OUAAAa9SURBVHja7d1bctpAEAVQQ7FqLYBtk48UVYRgIc17ps/5TWwztu6dlgDx8wMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAVHbxK/jftm0Pv4V53e93x7UCEHaUggIQepSBAhB6lEGwAhB6lEHAAhB8FEHQAhB+lEDAAhB8FEHAAhB8FEHQAhB+lEDQAhB+lEDAAhB8FEHQAhB+lEDQAhB+lEDQAhB+lEDQAhB+lEDQAhB+lEDQAhB+lEDQAhB+lEAbV38uiGu4prL7YwoIWgDCjxIIWgDCjxJwDQCINgHY/TEFmACAaBOA3R9TQNACaB1+HwiB4+9fN82LXTnuJNotHC1+4cKPYzLoBLD3i3bdwa7/279FOzZukRYr+DyPAdPhX9coQRR+RjgeRjsOl3wdwHu7Cz9HwxhtMvBCIHAKsO4IZPfH6agJAFAAQLcCMI7DWFkwAYAJAFAAgAIAFEBRLgDCeJkwAYAJAFAAgAIAFACgAAAFACgAQAEACgBQAIACABQAoAAABQAoAGA8N78Ccp39OC03h1EABAr8t69XCAqAxUN/9HsrAwVAkODv/TxFoACYJPhnw3rkeysCBcCgwc8N5fvX7/1MRaAAGCD8NQP4+r1/ewz3+/2iBMrzOgB2g7dt26Nl8PZ+XuvrEQqAEMHfC3+vx7VXAopAAbDQrm8aUAAMFP7S1w9yg6sE6nERUPiTg58awJQX/jz/3/vPdHHQBEDj8Jc8Dz/7vT49VpOAAiDjPLtH8HO+tx1fAVBg9z8b/laPMeXFSKYABUCl8Lfedc9eF1ACCoAGu3eLEsj9GUpAAVAxZK1eEjziZKIACDf6twpdyVIyBSgABh/V7eIKgAl3/1GnCVOAAiAjaKmBGel6gklCAZAR6JYl0CKspgAFwMndv3YJlHinYa8blygATA8e21K8GzB4gEvfgqvmzUXeJ5Uj39O7BRWA8T8jcEdDdub/1H7r8bZtD5OCUwAKB7fUSH72a/amFH9FEwANiuLo8+xH795z9HbfQm4CoOLuWvJUYy/MKVf/S950xJGhAJz/FwpFzisMj04SufcYdOHPKQCJ5VAr/K9f8+2KvgCbAFi0RARcAQAKgJnkPpe/NwW4UKcAAAUAKABAAQAKAKjAC4GCSvkYrtcX8JR4m+3eswq5nxeICSCk1E/VyS2KHuV0dB0KQgHY5SsUSsn3E5QOrNcVKAAqhL/E7bePvJ/Arq0A6Hi6cObfcm5BlvoYlIQCoFIwUm/ZtVcERz/2e4S1ROJZgADXAb7tsimv8X/+v9/u9FMymJ/u8Vfr2oQJAJPDIl+DCSB0mGvsxt++vuYdep5rKv1UpwIg9GlAryki5XG5iahTACYfm18vCNYKrN1fAdBoZzxzhf/T/3P3XwVAgymgRkBGCW/puxUpAGi849rBFQCTTAG1wpr70mK7vwKgcgnU3qlTX1os/AqAgyWw2pgu/AqAhqN2zSCPfK8CBUDYU4GUD/is9b2N/gqATtcDShZB7qcGC38eLwUOXAK59/dLLZKc0Aq/CYBKk0DOOXXtN+i8Pz7hVwBUGMFHvLD2HnzhVwAMPA2UDL5dXwHQoQR6TwNn7gBEOhcB+VgC72/XbRVAwVcADFwENUL5adIQfAXAgEVQYpeu/aEgKAAqXh9IvROw0CsAFiuDlAIQeAXAwoXAPDwNCAoAUACAAgAUAKAAAAUAKABAAQAKAFAAgAIAFACgAAAFACgAQAEACgBQAIACABQAoAAABQAoAGCBAhjh8+ZhJCNkwgQAJgBAAQAKAFAAxbkQCGNl4eoXjSCaAICAurTftm2P1s3e4mcy/+7f49g0ARj1cDx0cYv4RzcNCD4dTwEijlqML+IxefUHhbjHyi3SH9ZEgNAPcgrgD0A0I25AXgcAJgBTAETb/YcoACWA8DsFAKJOAKYA7P4mACDqBGAKwO4fvACUAMIfvACUAMLvGgAQdQIwBWD3D14ASgDhD14ASgDhD14ASgDhD14ASgDhBwAIdwow26nB62i46unLSms8upbVRv6L4AuG8J9fyypFcBF+wRD+tLWsUAIX4RcM4U9fy+wl4L0AgiH8gZ9inqq9XPCzxhHXMvMUYAIQDOEPTAE4mIRfAeBgEn4FgINJ+BUAwi/8CgDhF34FgPBbiwIQfuG3FgUg/NZoLQpA+K3RWhSA8FujtSgAwbBGa1EAgmGN1qIAHEzWaC2TcEMQ4beWQo/DBOBgskZrMQHMNAUIv7VE3f2nLYBSf3zht5bI4Z+6AHIPBOG3lsjBBwAAAAAAAAAAAAAAAAAAAAAAAAAAOvkDCUZjzFo5CksAAAAASUVORK5CYII='
);

-- Insert the Interest item icon
insert into "icon"(
    icon_uuid,
    icon_category_id,
    icon_name,
    icon_base64
) values(
    '8c04c875-c42c-4644-9b86-be9baacfbe7f',
    (select max(icon_category_id) from "icon_category" where icon_category_name = 'Operations'),
    'Interest',
    'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAAAXNSR0IB2cksfwAAAARnQU1BAACxjwv8YQUAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAZiS0dEAP8AAAAAMyd88wAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAAd0SU1FB+kIEAwXBngMJHMAAANdSURBVHja7Z3bdqsgFEVBzclH8+xH5wwJfUlHbRoVQXDLnvO1Hbm4ZmBzU2MAAAAAAEAXlkuwTgihy77I1j6lfr+BiNfDd875A16nlypBR8y6QYCNpnscx6xWchzHgS7g2hL4EEIf8a+9c+7/px6AIrD9WqF3zk0Lv36PAISPAISPAISPAISPAISPAISPAISPAISPAMsXvNgU9d5p2ZXw/xlj/NHvV5tBYvhHrMCtBRobyuuzTJ/+tjDtm/V+Z8BikHIQoCDSVwJFdgGiCiRrn5ErgZfs/0UKMLvodqn/rf15mpZc+PBrbxfVrxVnLQzbmAfYOVwjfAUCEL5iAQhfsQCEr1gAwlcsAOErFoDwFQtA+IoFIHzFAhC+YgEIX7EAhK9QgNkCkN0IP2pL1p8v3Pjq3qUFKL0d7CVOjwQ/sCMIAQABgBrgKpV/Rt8/GGMC/f8FhoElDoYQPAAAAAAAAABoJ2oiqNQdO5icOZ8hIvy+1CndEAIbOiS3ACXD/4Y5eqEC1Nic8S4CrUF9xCwHO+emnLtxwMUFmEnAHgWtAoAgAY54Xg5cvAWw1nokUDwMnI8IEl+/j72jJiMBwQKksHcOgfAbEoDwFQtA+IoFIHzFAhC+YgEIX7EAhK9YAMJXLADhKxaA8BULQPiKBSB8xQIQvmIBCF+xAISvWICE8JPu3WcMh0TECVDjTAAth1ABap8JQILzYFs4AoBmmtvxm9qCpBah8/ebpsl4v96D3e93UUXv0Fj4yYXrnpPKsXc033o/I+BQrJhRQG4ReMTnXbuT+BGhSyx8h5Um0YcQhloSZIbfFbyHQWcKPsn8VfieJsGw0S/6Wid2hfSJ9q2GqPII+++T0bHXYKnOSbmGw0WCqTYMvUCBuyhlSkvCuT8BvLq/Z243lNKdIICM8P1R9cfe7gQBTgx+aRhYuvBEAKG/+prBI4CMgs7UHnEgAKONX7AYpBwEQADQNKeAAErnFCgClc4pIADBIwDBIwDBI8C1QzemzNI8AixcbHPS1GzJXzsCbPPrgn/YDWVbe4KKbSa5zE2hsWPoUos4Zz06xzb1802UIHdn7sIevSgxzn5mUlMCvIfxeDxW/7frOnO73Yrte4w5pMKpaAAAAAAAAAAAAAAAAAAAAAAAOI4v1djqMECXTEAAAAAASUVORK5CYII='
);


-- Create the Operations category
insert into "income_or_expense_category"(
    income_or_expense_category_uuid,
    income_or_expense_category_name,
    income_or_expense_category_description,
    income_or_expense_category_icon_uuid
) values (
    '8b307e9c-250c-4bf0-a3bd-2717f8075820',
    'Operations',
    'Usual bank-related operations',
    'f83a0ae7-b9eb-45f9-8c05-f154026d5112'
);

-- Create the Income category
insert into "income_or_expense_category"(
    income_or_expense_category_uuid,
    income_or_expense_category_name,
    income_or_expense_category_description,
    income_or_expense_category_icon_uuid
) values (
    '2caaa6bf-2382-465f-a663-2d3d5d3205b0',
    'Income',
    'Active and passive income items',
    '6821b056-7d7e-4eef-a719-5a29dff6d0e3'
);

-- Create the Inter-account operations sub-category
insert into "income_or_expense_subcategory"(
    income_or_expense_category_id,
    income_or_expense_subcategory_uuid,
    income_or_expense_subcategory_name,
    income_or_expense_subcategory_description,
    income_or_expense_subcategory_icon_uuid
) values (
    (select max(income_or_expense_category_id) from "income_or_expense_category" where "income_or_expense_category_uuid" = '8b307e9c-250c-4bf0-a3bd-2717f8075820'),
    'a9c3c952-c9af-45ce-97f7-eeddce130042',
    'Inter-account operations',
    'Operations that involve two or more accounts',
    '147b99f5-74a3-469a-b67d-589400a93e88'
);

-- Create the Passive income sub-category
insert into "income_or_expense_subcategory"(
    income_or_expense_category_id,
    income_or_expense_subcategory_uuid,
    income_or_expense_subcategory_name,
    income_or_expense_subcategory_description,
    income_or_expense_subcategory_icon_uuid
) values (
    (select max(income_or_expense_category_id) from "income_or_expense_category" where "income_or_expense_category_uuid" = '2caaa6bf-2382-465f-a663-2d3d5d3205b0'),
    '3e2a8f78-a097-4b69-bb40-ca7fdef76b60',
    'Passive income',
    'Income that gets generated from assets',
    '6f977843-99dd-442b-b5c3-811e6edf450c'
);

-- Create the Transfer item
insert into "income_or_expense_item"(
    income_or_expense_subcategory_id,
    income_or_expense_item_uuid,
    income_or_expense_item_name,
    income_or_expense_item_description,
    income_or_expense_item_icon_uuid
) values (
    (select max(income_or_expense_subcategory_id) from "income_or_expense_subcategory" where "income_or_expense_subcategory_uuid" = 'a9c3c952-c9af-45ce-97f7-eeddce130042'),
    '9eacf1d5-5631-45e6-a7a3-cfc522054307',
    'Transfer',
    'Transfer of an amount between two accounts with the same currency',
    'f5e05aa5-c76d-4d01-8f80-5a4f4ee2ff58'
);

-- Create the Exchange item
insert into "income_or_expense_item"(
    income_or_expense_subcategory_id,
    income_or_expense_item_uuid,
    income_or_expense_item_name,
    income_or_expense_item_description,
    income_or_expense_item_icon_uuid
) values (
    (select max(income_or_expense_subcategory_id) from "income_or_expense_subcategory" where "income_or_expense_subcategory_uuid" = 'a9c3c952-c9af-45ce-97f7-eeddce130042'),
    'ea8d5742-3751-4596-8255-edc7623a59e9',
    'Exchange',
    'Transfer of an amount between two accounts with different currencies',
    '08de4d2e-309d-48e0-bc95-2a6ee91a3b78'
);

-- Create the Deposit item
insert into "income_or_expense_item"(
    income_or_expense_subcategory_id,
    income_or_expense_item_uuid,
    income_or_expense_item_name,
    income_or_expense_item_description,
    income_or_expense_item_icon_uuid
) values (
    (select max(income_or_expense_subcategory_id) from "income_or_expense_subcategory" where "income_or_expense_subcategory_uuid" = 'a9c3c952-c9af-45ce-97f7-eeddce130042'),
    'c8c2cef2-0781-44a0-bdd6-77697e73e8e3',
    'Deposit',
    'Creation or destruction of a special deposit account',
    '415be7b8-93c6-4fe7-9323-a7297e281e53'
);

-- Create the Interest item
insert into "income_or_expense_item"(
    income_or_expense_subcategory_id,
    income_or_expense_item_uuid,
    income_or_expense_item_name,
    income_or_expense_item_description,
    income_or_expense_item_icon_uuid
) values (
    (select max(income_or_expense_subcategory_id) from "income_or_expense_subcategory" where "income_or_expense_subcategory_uuid" = '3e2a8f78-a097-4b69-bb40-ca7fdef76b60'),
    'f703e387-8fba-4529-bdcb-ed66561758e9',
    'Interest',
    'Passive income usually generated by deposits',
    '8c04c875-c42c-4644-9b86-be9baacfbe7f'
);