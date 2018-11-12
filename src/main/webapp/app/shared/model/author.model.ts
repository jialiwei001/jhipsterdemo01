import { Moment } from 'moment';
import { IBook } from 'app/shared/model/book.model';

export interface IAuthor {
  id?: number;
  author?: string;
  name?: string;
  brithDate?: Moment;
  ns?: IBook[];
}

export const defaultValue: Readonly<IAuthor> = {};
