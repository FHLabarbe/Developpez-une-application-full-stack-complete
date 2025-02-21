export interface Article {
  id?: number;
  title: string;
  content: string;
  themeId: number;
  authorId?: number;
  createdAt?: Date;
}
