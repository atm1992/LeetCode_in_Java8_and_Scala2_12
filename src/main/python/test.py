# -*- coding: UTF-8 -*-


def transform_name(name: str) -> None:
    print('_'.join(name.split('-')))


if __name__ == '__main__':
    transform_name('a6_zigzag-conversion.Solution')
