package com.example.chatflow.presentation.chatlist;

import com.example.chatflow.domain.usecase.GetPagedChatsUseCase;
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase;
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase;
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ChatListViewModel_Factory implements Factory<ChatListViewModel> {
  private final Provider<GetPagedChatsUseCase> getPagedChatsProvider;

  private final Provider<ObserveCachedChatsUseCase> observeCachedChatsProvider;

  private final Provider<SyncChatsOnceUseCase> syncOnceProvider;

  private final Provider<ObserveApiTimeMsUseCase> observeApiTimeMsProvider;

  public ChatListViewModel_Factory(Provider<GetPagedChatsUseCase> getPagedChatsProvider,
      Provider<ObserveCachedChatsUseCase> observeCachedChatsProvider,
      Provider<SyncChatsOnceUseCase> syncOnceProvider,
      Provider<ObserveApiTimeMsUseCase> observeApiTimeMsProvider) {
    this.getPagedChatsProvider = getPagedChatsProvider;
    this.observeCachedChatsProvider = observeCachedChatsProvider;
    this.syncOnceProvider = syncOnceProvider;
    this.observeApiTimeMsProvider = observeApiTimeMsProvider;
  }

  @Override
  public ChatListViewModel get() {
    return newInstance(getPagedChatsProvider.get(), observeCachedChatsProvider.get(), syncOnceProvider.get(), observeApiTimeMsProvider.get());
  }

  public static ChatListViewModel_Factory create(
      Provider<GetPagedChatsUseCase> getPagedChatsProvider,
      Provider<ObserveCachedChatsUseCase> observeCachedChatsProvider,
      Provider<SyncChatsOnceUseCase> syncOnceProvider,
      Provider<ObserveApiTimeMsUseCase> observeApiTimeMsProvider) {
    return new ChatListViewModel_Factory(getPagedChatsProvider, observeCachedChatsProvider, syncOnceProvider, observeApiTimeMsProvider);
  }

  public static ChatListViewModel newInstance(GetPagedChatsUseCase getPagedChats,
      ObserveCachedChatsUseCase observeCachedChats, SyncChatsOnceUseCase syncOnce,
      ObserveApiTimeMsUseCase observeApiTimeMs) {
    return new ChatListViewModel(getPagedChats, observeCachedChats, syncOnce, observeApiTimeMs);
  }
}
