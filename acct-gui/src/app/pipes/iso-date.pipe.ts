import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'isoDate'
})
export class IsoDatePipe implements PipeTransform {

    transform(value: any, ...args: any[]): any {
      return new Date(value).toISOString().split("T")[0];
    }

}
